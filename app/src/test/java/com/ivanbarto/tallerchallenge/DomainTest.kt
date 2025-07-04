package com.ivanbarto.tallerchallenge

import com.ivanbarto.tallerchallenge.users.data.dto.UserDto
import com.ivanbarto.tallerchallenge.users.data.repository.UserRepository
import com.ivanbarto.tallerchallenge.users.domain.di.getDomainModules
import com.ivanbarto.tallerchallenge.users.domain.interactors.UserInteractor
import com.ivanbarto.tallerchallenge.users.domain.models.User
import com.ivanbarto.tallerchallenge.users.domain.models.toDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.Koin
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.verify.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DomainTest : KoinTest {

    private val userInteractorDependency: UserInteractor by inject()

    private val testModules = module {
        includes(getDomainModules())
    }

    //isolated test koin context to check isloated layer module
    private val isolatedTestKoinContext = koinApplication {
        modules(testModules)
    }

    override fun getKoin(): Koin = isolatedTestKoinContext.koin

    private val userRepository: UserRepository = mockk()
    private val userInteractor: UserInteractor = mockk()

    private val mixedResults = listOf(
        UserDto("User4", "surname4"),
        UserDto("User1", "surname1"),
        UserDto("User5", "surname5"),
        UserDto("User2", "surname2")
    )

    private val orderedResults = listOf(
        User("User1", "surname1"),
        User("User2", "surname2"),
        User("User4", "surname4"),
        User("User5", "surname5")
    )

    @Before
    fun setup() {
        coEvery { userRepository.getUser() }
            .answers { mixedResults }
        coEvery { userInteractor.getUsersAlphabetically() }
            .answers {
                runBlocking {
                    userRepository.getUser().map { it.toDomain() }
                        .sortedBy { user -> user.name + user.surname }
                }
            }
    }

    @After
    fun cleanup() {
        stopKoin()
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun testDataModules() {
        testModules.verify()
        assertNotNull(userInteractorDependency)
    }

    @Test
    fun users_are_loaded() = runBlocking {
        val data = userInteractor.getUsersAlphabetically()

        assertEquals(orderedResults, data)
    }
}