package ConSilkTea.SmallRecordServer.barcode.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BarcodeDtoRequest(
    @field:NotNull
    @JsonProperty("barcodeNum")
    private val _barcodeNum: Long?
) {
    val barcodeNum: Long
        get() = _barcodeNum!!
}

data class BarcodeDtoResponse(
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
)