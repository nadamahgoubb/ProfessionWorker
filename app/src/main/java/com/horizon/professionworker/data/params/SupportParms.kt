package com.horizon.professionworker.data.params


data class ContactUsParms(

  val  countryCode : String,
  val  phone: String,
   val  content: String ="",
)

data class ComplainParms(

  val  providerId: String = "",
   val  title: String ="",
   val  content: String ="",
)