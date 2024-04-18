package com.horizon.professionworker.data.repo

import androidx.annotation.Keep
import androidx.room.*



@Keep

@Dao
interface RoomDao {
/*

    @Query("SELECT * FROM itemprices")
    fun loadItemprices(): List<Itemprices>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun inserItemsPrice(item: Itemprices): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAllItemsPrice(order: List<Itemprices?>?)

    @Delete
    suspend fun deleteItemService(product: Itemprices)

    @Query("SELECT * FROM ItempricesAditionalservices")
    fun loadItemAdditionalprices(): List<ItempricesAditionalservices>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun inserAdditionalItemsPrice(item: ItempricesAditionalservices): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAdditionalAllItemsPrice(order: List<ItempricesAditionalservices?>?)

    @Delete
    suspend fun deleteAdditionalItemService(product: ItempricesAditionalservices)

    @Query("DELETE FROM ItempricesAditionalservices")
    suspend fun deleteAllAdditionalServices()

    @Query("DELETE FROM itemprices")
    suspend fun deletAllServices()


//edit screen


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertPrices(item: SendPriceEditService): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAllItemsPrices(items: List<SendPriceEditService?>?)

    @Delete
    suspend fun deletePriceEditService(item: SendPriceEditService)

    @Query("DELETE FROM SendPriceEditService")
    suspend fun deleteAllPriceServices()

    @Query("SELECT * FROM sendpriceeditservice")
    fun loadIAllItemsPrices(): List<SendPriceEditService>


    //orders

    //service

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertServicepricesOrder(item: ServicepricesOrder): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAllServicepricesOrder(items: List<ServicepricesOrder?>?)

    @Delete
    suspend fun deletePServicepricesOrder(item: ServicepricesOrder)

    @Query("DELETE FROM ServicepricesOrder")
    suspend fun deleteAllServicepricesOrder()


    @Query("SELECT * FROM ServicepricesOrder")
    fun loadEditServiceprices(): List<ServicepricesOrder>


    //add service

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAddServicepricesOrder(item: AdditionalservicesOrder): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertAllAdditionalservicesOrder(items: List<AdditionalservicesOrder?>?)

    @Delete
    suspend fun deleteAdditionalservicesOrder(item: AdditionalservicesOrder)

    @Query("DELETE FROM AdditionalservicesOrder")
    suspend fun deleteAdditionalservicesOrder()


    @Query("SELECT * FROM AdditionalservicesOrder")
    fun loadAdditionalservicesOrder(): List<AdditionalservicesOrder>*/
}