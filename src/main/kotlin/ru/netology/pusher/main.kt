package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    sendLikeNotification()

    sendNewPostNotification()
}

fun sendLikeNotification() {
    val message = Message.builder()
        .putData("action", "like")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""")
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}

fun sendNewPostNotification() {
    val message = Message.builder()
        .putData("action", "newPost")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postContent": "Таймбоксинг - отличный способ навести порядок в своем календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип - на каждое дело заранее выделяется определенный отрезок времени. В это время вы работаете только над одной задачей, не переключаась на другие. Собрали советы, которые помогут внедрить таймбоксинг"
        }""")
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
