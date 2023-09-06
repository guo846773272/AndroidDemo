package com.example.basicviewsactivitydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Book(
    val title: String,
    val author: String
)

data class User(
    val name: String,
    val age: Int,
    val email: String,
    val favoriteBook: Book
)

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GsonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GsonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        toModel0()
        toModel1()
    }

    fun toModel0() {

        // JSON 数据
        val json = """
{
  "name": "John",
  "age": 30,
  "email": "john@example.com",
  "favoriteBook": {
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald"
  }
}
"""

        // 使用 Gson 解析 JSON 数据为 User 对象
        val gson = Gson()
        val user = gson.fromJson(json, User::class.java)

        // 访问 User 对象的属性，包括嵌套的 Book 对象
        println("Favorite Book: ${user.favoriteBook.title} by ${user.favoriteBook.author}")

        println("Name: ${user.name}")
        println("Age: ${user.age}")
        println("Email: ${user.email}")
    }

    fun toModel1() {

        // JSON 数据字符串
        val json = """
[
  {
    "name": "John",
    "age": 30,
    "email": "john@example.com",
    "favoriteBook": {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald"
    }
  },
  {
    "name": "Alice",
    "age": 25,
    "email": "alice@example.com",
    "favoriteBook": {
      "title": "To Kill a Mockingbird",
      "author": "Harper Lee"
    }
  },
  {
    "name": "Bob",
    "age": 35,
    "email": "bob@example.com",
    "favoriteBook": {
      "title": "1984",
      "author": "George Orwell"
    }
  }
]
"""

        // 使用 Gson 解析 JSON 数组为包含嵌套 User 对象的数组
        val gson = Gson()
        val userListType = object : TypeToken<List<User>>() {}.type
        val users: List<User> = gson.fromJson(json, userListType)

        // 遍历用户数组并访问嵌套的 Book 对象
        for (user in users) {
            println("Name: ${user.name}")
            println("Age: ${user.age}")
            println("Email: ${user.email}")
            println("Favorite Book: ${user.favoriteBook.title} by ${user.favoriteBook.author}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gson, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GsonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GsonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}