package com.cmm.wdflowers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cmm.wdflowers.datasource.model.Order
import com.cmm.wdflowers.datasource.model.Resource
import com.cmm.wdflowers.datasource.model.Status
import com.cmm.wdflowers.di.modules.appModule
import com.cmm.wdflowers.di.modules.repositoryModule
import com.cmm.wdflowers.di.modules.viewModelsModules
import com.cmm.wdflowers.repositories.Repository
import com.cmm.wdflowers.ui.orders.OrdersViewModel
import com.cmm.wdflowers.ui.orders.model.OrderUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class OrdersViewModelTest : KoinTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var ordersObserver: Observer<List<OrderUiModel>?>

    @Mock
    private lateinit var orderByIdObserver: Observer<OrderUiModel>

    @Mock
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private lateinit var viewModel: OrdersViewModel

    @Before
    fun setup() {
        startKoin {
            modules(listOf(appModule, repositoryModule, viewModelsModules))
        }
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = OrdersViewModel(repository)
        viewModel.orders().observeForever(ordersObserver)
        viewModel.orderById().observeForever(orderByIdObserver)
    }

    @After
    fun teardown() {
        stopKoin()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getOrders_null_payload() = runBlockingTest {
        Mockito.`when`(repository.getOrders()).thenReturn(Resource(null, Status.Error()))

        viewModel.getOrders()
        verify(ordersObserver).onChanged(null)
    }

    @Test
    fun getOrders_empty_payload() = runBlockingTest {
        Mockito.`when`(repository.getOrders()).thenReturn(Resource(emptyList(), Status.Success))

        viewModel.getOrders()
        verify(ordersObserver).onChanged(emptyList())
    }

    @Test
    fun getOrders_payload() = runBlockingTest {
        val expectedResult = listOf(
            OrderUiModel(1, "Description1", 1, "Destination1"),
            OrderUiModel(2, "Description2", 2, "Destination2")
        )
        val mockedApiPayload = listOf(
            Order(1, "Description1", 1, "Destination1"),
            Order(2, "Description2", 2, "Destination2")
        )

        Mockito.`when`(repository.getOrders())
            .thenReturn(Resource(mockedApiPayload, Status.Success))

        viewModel.getOrders()

        verify(ordersObserver).onChanged(expectedResult)
    }

    @Test
    fun getOrdersById_payload() = runBlockingTest {
        val expectedResult = OrderUiModel(2, "Description2", 2, "Destination2")
        val requestedOrderId = 2
        val mockedPayload = Order(2, "Description2", 2, "Destination2")
        val mockedApiPayload = listOf(
            Order(1, "Description1", 1, "Destination1"),
            Order(2, "Description2", 2, "Destination2")
        )

        Mockito.`when`(repository.getOrders())
            .thenReturn(Resource(mockedApiPayload, Status.Success))
        Mockito.`when`(repository.getOrderById(requestedOrderId)).thenReturn(mockedPayload)

        viewModel.getOrders()
        viewModel.getOrder(requestedOrderId)

        verify(orderByIdObserver).onChanged(expectedResult)
    }
}