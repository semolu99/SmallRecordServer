package ConSilkTea.SmallRecordServer.barcode.entity

import ConSilkTea.SmallRecordServer.barcode.dto.BarcodeDtoResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Food(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val No: Long? = null,
    val barcodeNum: Long,
    val product_name: String,
    val manufacturer: String,
    val Total_capacity_g: Long,
    val calorie_kcal: Long,
    val protein_g: Long,
    val fat_g: Long,
    val Carbohydarte_g: Long,
    val total_sugars_g: Long,
    val sodium_mg: Long,
    val Cholesterol_mg: Long,
) {
    fun toDto(): BarcodeDtoResponse =
        BarcodeDtoResponse(
            No!!,
            barcodeNum,
            product_name,
            manufacturer,
            Total_capacity_g,
            calorie_kcal,
            protein_g,
            fat_g,
            Carbohydarte_g,
            total_sugars_g,
            sodium_mg,
            Cholesterol_mg
        )
}