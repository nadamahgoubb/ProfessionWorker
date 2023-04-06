package com.example.profession.data.dataSource.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id"                         ) var id                       : Int?                   = null,
    @SerializedName("name"                       ) var name                     : String?                = null,
    @SerializedName("type"                       ) var type                     : String?                = null,
    @SerializedName("national_id"                ) var nationalId               : String?                = null,
    @SerializedName("commercial_registration_no" ) var commercialRegistrationNo : String?                = null,
    @SerializedName("country_id"                 ) var countryId                : Int?                   = null,
    @SerializedName("country_name"               ) var countryName              : String?                = null,
    @SerializedName("city_id"                    ) var cityId                   : Int?                   = null,
    @SerializedName("city_name"                  ) var cityName                 : String?                = null,
    @SerializedName("nationality_id"             ) var nationalityId            : Int?                   = null,
    @SerializedName("nationality_name"           ) var nationalityName          : String?                = null,
    @SerializedName("lat"                        ) var lat                      : String?                = null,
    @SerializedName("lon"                        ) var lon                      : String?                = null,
    @SerializedName("address"                    ) var address                  : String?                = null,
    @SerializedName("phone"                      ) var phone                    : String?                = null,
    @SerializedName("country_code"               ) var countryCode              : String?                = null,
    @SerializedName("email"                      ) var email                    : String?                = null,
    @SerializedName("service_id"                 ) var serviceId                : Int?                   = null,
    @SerializedName("service_name"               ) var serviceName              : String?                = null,
    @SerializedName("previous_experience"        ) var previousExperience       : String?                = null,
    @SerializedName("years_experience"           ) var yearsExperience          : String?                = null,
    @SerializedName("hour_price"                 ) var hourPrice                : Double?                = null,
    @SerializedName("photo"                      ) var photo                    : String?                = null,
    @SerializedName("account_number"             ) var accountNumber            : String?                = null,
    @SerializedName("account_name"               ) var accountName              : String?                = null,
    @SerializedName("bank_id"                    ) var bankId                   : Int?                   = null,
    @SerializedName("bank_name"                  ) var bankName                 : String?                = null,
    @SerializedName("iban_number"                ) var ibanNumber               : String?                = null,
    @SerializedName("balance"                    ) var balance                  : Int?                   = null,
    @SerializedName("avg_rate"                   ) var avgRate                  : Int?                   = null,
    @SerializedName("count_reviews"              ) var countReviews             : Int?                   = null,
    @SerializedName("mobile_id"                  ) var mobileId                 : Int?                   = null,
    @SerializedName("sub_services"               ) var subServices              : ArrayList<SubServices> = arrayListOf()

)


data class SubServices (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("service_id" ) var serviceId : Int?    = null,
    @SerializedName("icon"       ) var icon      : String? = null,
    @SerializedName("active"     ) var active    : Int?    = null,
    @SerializedName("name"       ) var name      : String? = null

)