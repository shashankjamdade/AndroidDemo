package com.matrimony.demo.com.matrimony.demo.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.matrimony.demo.db.AppDatabase
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.getOrAwaitValue
import com.matrimony.demo.model.Name
import com.matrimony.demo.model.ResultUserItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUserList() = runBlockingTest {
        var resultItem1 = "{\n" +
                "        \"cell\": \"(793)-516-4016\",\n" +
                "        \"dob\": {\n" +
                "            \"age\": \"53\",\n" +
                "            \"date\": \"1967-08-12T12:50:14.765Z\"\n" +
                "        },\n" +
                "        \"email\": \"melike.velioglu@example.com\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"userId\" :\"1\",\n" +
                "        \"id\": {\n" +
                "            \"name\": \"\"\n" +
                "        },\n" +
                "        \"location\": {\n" +
                "            \"city\": \"Çorum\",\n" +
                "            \"coordinates\": {\n" +
                "                \"latitude\": \"77.0503\",\n" +
                "                \"longitude\": \"53.7232\"\n" +
                "            },\n" +
                "            \"country\": \"Turkey\",\n" +
                "            \"postcode\": \"26636\",\n" +
                "            \"state\": \"Ankara\",\n" +
                "            \"street\": {\n" +
                "                \"name\": \"Şehitler Cd\",\n" +
                "                \"number\": \"9763\"\n" +
                "            },\n" +
                "            \"timezone\": {\n" +
                "                \"description\": \"Eastern Australia, Guam, Vladivostok\",\n" +
                "                \"offset\": \"+10:00\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"login\": {\n" +
                "            \"md5\": \"b95e1897ac1af9a20d95a17a0e25f210\",\n" +
                "            \"password\": \"mexican\",\n" +
                "            \"salt\": \"erwGCPCT\",\n" +
                "            \"sha1\": \"1e6d4731adf5b6e76b929fb76e693df058c3f3e2\",\n" +
                "            \"sha256\": \"43e0f7609ddc414c5ada490bfd6b8f40bbcb2a3d43495c9c81d6f3e01fad3616\",\n" +
                "            \"username\": \"brownduck528\",\n" +
                "            \"uuid\": \"adcff531-2145-487a-bb93-3764f327f337\"\n" +
                "        },\n" +
                "        \"name\": {\n" +
                "            \"first\": \"Melike\",\n" +
                "            \"last\": \"Velioğlu\",\n" +
                "            \"title\": \"Ms\"\n" +
                "        },\n" +
                "        \"nat\": \"TR\",\n" +
                "        \"phone\": \"(214)-912-4414\",\n" +
                "        \"picture\": {\n" +
                "            \"large\": \"https://randomuser.me/api/portraits/women/95.jpg\",\n" +
                "            \"medium\": \"https://randomuser.me/api/portraits/med/women/95.jpg\",\n" +
                "            \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/95.jpg\"\n" +
                "        },\n" +
                "        \"registered\": {\n" +
                "            \"age\": \"13\",\n" +
                "            \"date\": \"2007-08-18T10:08:47.334Z\"\n" +
                "        },\n" +
                "        \"userChoice\": \"accepted\",\n" +
                "        \"userId\": \"0\"\n" +
                "    }"
        var resultItemObj1 =
            Gson().fromJson<ResultUserItem>(resultItem1, ResultUserItem::class.java)
        resultItemObj1.userId = "1"
        var userList = arrayListOf<ResultUserItem>();
        userList.add(resultItemObj1)
        dao.insertUserList(userList)
        val allUsers = dao.loadUserList().getOrAwaitValue()
        assertThat(allUsers).contains(resultItemObj1)
    }

    @Test
    fun updateUser() = runBlockingTest {
        var resultItem1 = "{\n" +
                "        \"cell\": \"(793)-516-4016\",\n" +
                "        \"dob\": {\n" +
                "            \"age\": \"53\",\n" +
                "            \"date\": \"1967-08-12T12:50:14.765Z\"\n" +
                "        },\n" +
                "        \"email\": \"melike.velioglu@example.com\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"userId\" :\"1\",\n" +
                "        \"id\": {\n" +
                "            \"name\": \"\"\n" +
                "        },\n" +
                "        \"location\": {\n" +
                "            \"city\": \"Çorum\",\n" +
                "            \"coordinates\": {\n" +
                "                \"latitude\": \"77.0503\",\n" +
                "                \"longitude\": \"53.7232\"\n" +
                "            },\n" +
                "            \"country\": \"Turkey\",\n" +
                "            \"postcode\": \"26636\",\n" +
                "            \"state\": \"Ankara\",\n" +
                "            \"street\": {\n" +
                "                \"name\": \"Şehitler Cd\",\n" +
                "                \"number\": \"9763\"\n" +
                "            },\n" +
                "            \"timezone\": {\n" +
                "                \"description\": \"Eastern Australia, Guam, Vladivostok\",\n" +
                "                \"offset\": \"+10:00\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"login\": {\n" +
                "            \"md5\": \"b95e1897ac1af9a20d95a17a0e25f210\",\n" +
                "            \"password\": \"mexican\",\n" +
                "            \"salt\": \"erwGCPCT\",\n" +
                "            \"sha1\": \"1e6d4731adf5b6e76b929fb76e693df058c3f3e2\",\n" +
                "            \"sha256\": \"43e0f7609ddc414c5ada490bfd6b8f40bbcb2a3d43495c9c81d6f3e01fad3616\",\n" +
                "            \"username\": \"brownduck528\",\n" +
                "            \"uuid\": \"adcff531-2145-487a-bb93-3764f327f337\"\n" +
                "        },\n" +
                "        \"name\": {\n" +
                "            \"first\": \"Melike\",\n" +
                "            \"last\": \"Velioğlu\",\n" +
                "            \"title\": \"Ms\"\n" +
                "        },\n" +
                "        \"nat\": \"TR\",\n" +
                "        \"phone\": \"(214)-912-4414\",\n" +
                "        \"picture\": {\n" +
                "            \"large\": \"https://randomuser.me/api/portraits/women/95.jpg\",\n" +
                "            \"medium\": \"https://randomuser.me/api/portraits/med/women/95.jpg\",\n" +
                "            \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/95.jpg\"\n" +
                "        },\n" +
                "        \"registered\": {\n" +
                "            \"age\": \"13\",\n" +
                "            \"date\": \"2007-08-18T10:08:47.334Z\"\n" +
                "        },\n" +
                "        \"userChoice\": \"accepted\",\n" +
                "        \"userId\": \"0\"\n" +
                "    }"
        var resultItemObj1 =
            Gson().fromJson<ResultUserItem>(resultItem1, ResultUserItem::class.java)
        resultItemObj1.userId = "1"
        var userList = arrayListOf<ResultUserItem>();
        userList.add(resultItemObj1)
        dao.insertUserList(userList)
        //now update same object
        resultItemObj1?.name?.first = "Shashank"
        dao.updateUser(resultItemObj1)
        val allUsers = dao.loadUserList().getOrAwaitValue()
        assertThat(allUsers[0]?.name?.first).isEqualTo("Shashank")
    }

}

