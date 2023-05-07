package com.example.professionworker.data.params



data class SupportParms(

  val  app_type: Int = 1,
   val  content: String ="",
)

data class ComplainParms(

  val  providerId: String = "",
   val  title: String ="",
   val  content: String ="",
)