package com.ivanbarto.tallerchallenge

import com.ivanbarto.tallerchallenge.users.data.di.getDataModules
import com.ivanbarto.tallerchallenge.users.data.dto.UserDto
import com.ivanbarto.tallerchallenge.users.data.repository.UserRepository
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
import kotlin.test.assertNotNull


class DataTest : KoinTest {

    private val userRepositoryDependency: UserRepository by inject()

    private val testModules = module {
        includes(getDataModules())
    }

    //isolated test koin context to check proper layer module
    private val isolatedTestKoinContext = koinApplication {
        modules(testModules)
    }

    override fun getKoin(): Koin = isolatedTestKoinContext.koin

    private val results = let {
        val list = mutableListOf<UserDto>()
        repeat(30) {
            list.add(mockk(relaxed = true))
        }
        list.toList()
    }

    private val userRepository: UserRepository = mockk()

    @Before
    fun setup() {
        coEvery { userRepository.getUser() }
            .answers { results }
    }

    @After
    fun cleanup() {
        stopKoin()
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun testDataModules() {
        testModules.verify()
        assertNotNull(userRepositoryDependency)
    }

    @Test
    fun users_are_loaded() = runBlocking {
        val data = userRepository.getUser()

        assert(data.isNotEmpty())
        assert(data.count() == 30)
    }
}