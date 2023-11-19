package ConSilkTea.SmallRecordServer.barcode.repository

import ConSilkTea.SmallRecordServer.barcode.entity.Food
import org.springframework.data.jpa.repository.JpaRepository

interface BarcodeRepository : JpaRepository<Food, Long> {
    fun findByBarcodeNum(barcodeNum: Long): Food?
}