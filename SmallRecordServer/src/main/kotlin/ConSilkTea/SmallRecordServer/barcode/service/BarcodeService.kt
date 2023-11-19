package ConSilkTea.SmallRecordServer.barcode.service

import ConSilkTea.SmallRecordServer.barcode.dto.BarcodeDtoRequest
import ConSilkTea.SmallRecordServer.barcode.dto.BarcodeDtoResponse
import ConSilkTea.SmallRecordServer.barcode.entity.Food
import ConSilkTea.SmallRecordServer.barcode.repository.BarcodeRepository
import ConSilkTea.SmallRecordServer.common.exception.InvalidInputException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class BarcodeService(
    private val barcodeRepository: BarcodeRepository
) {
    /**
     * 바코드 인식해서 음식정보 빼오기
     */
    fun searchBarcodeInfo(barcodeNum: Long): BarcodeDtoResponse {
        var food: Food? = barcodeRepository.findByBarcodeNum(barcodeNum)
        if (food == null) {
            throw InvalidInputException("barcode", "없는 제품입니다.")
        }
        return food.toDto()
    }
}