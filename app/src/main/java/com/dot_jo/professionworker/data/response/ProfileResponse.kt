package com.dot_jo.professionworker.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id"                         ) var id                       : String?                   = null,
    @SerializedName("name"                       ) var name                     : String?                = null,
    @SerializedName("type"                       ) var type                     : String?                = null,
    @SerializedName("national_id"                ) var nationalId               : String?                = null,
    @SerializedName("commercial_registration_no" ) var commercialRegistrationNo : String?                = null,
    @SerializedName("country_id"                 ) var countryId                : String?                   = null,
    @SerializedName("country_name"               ) var countryName              : String?                = null,
    @SerializedName("city_id"                    ) var cityId                   : String?                   = null,
    @SerializedName("city_name"                  ) var cityName                 : String?                = null,
    @SerializedName("nationality_id"             ) var nationalityId            : String?                   = null,
    @SerializedName("nationality_name"           ) var nationalityName          : String?                = null,
    @SerializedName("lat"                        ) var lat                      : String?                = null,
    @SerializedName("lon"                        ) var lon                      : String?                = null,
    @SerializedName("address"                    ) var address                  : String?                = null,
    @SerializedName("phone"                      ) var phone                    : String?                = null,
    @SerializedName("country_code"               ) var countryCode              : String?                = null,
    @SerializedName("email"                      ) var email                    : String?                = null,
    @SerializedName("service_id"                 ) var serviceId                : String?                   = null,
    @SerializedName("service_name"               ) var serviceName              : String?                = null,
    @SerializedName("previous_experience"        ) var previousExperience       : String?                = null,
    @SerializedName("years_experience"           ) var yearsExperience          : String?                = null,
    @SerializedName("hour_price"                 ) var hourPrice                : Double?                = null,
    @SerializedName("photo"                      ) var photo                    : String?                = null,
    @SerializedName("account_number"             ) var accountNumber            : String?                = null,
    @SerializedName("account_name"               ) var accountName              : String?                = null,
    @SerializedName("bank_id"                    ) var bankId                   : String?                   = null,
    @SerializedName("bank_name"                  ) var bankName                 : String?                = null,
    @SerializedName("iban_number"                ) var ibanNumber               : String?                = null,
    @SerializedName("balance"                    ) var balance                  : String?                   = null,
    @SerializedName("avg_rate"                   ) var avgRate                  : String?                   = null,
    @SerializedName("count_reviews"              ) var countReviews             : String?                   = null,
    @SerializedName("mobile_id"                  ) var mobileId                 : Int?                   = null,
    @SerializedName("sub_services"               ) var subServices              : ArrayList<ServicesItemsResponse> = arrayListOf()

)


data class NotificationResponse(

    @SerializedName("notifications"                ) var notifications              : ArrayList<NotificationsItem>?    = arrayListOf(),


    )

data class NotificationsItem(

    @SerializedName("id"                ) var id              : String?    = null,
    @SerializedName("body"              ) var body            : String? = null,
    @SerializedName("created_at"        ) var created_at       : String?    = null,

    var choosen: Boolean= false

)

