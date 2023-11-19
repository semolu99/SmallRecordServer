package ConSilkTea.SmallRecordServer.barcode.controller

import ConSilkTea.SmallRecordServer.barcode.dto.BarcodeDtoRequest
import ConSilkTea.SmallRecordServer.barcode.dto.BarcodeDtoResponse
import ConSilkTea.SmallRecordServer.barcode.service.BarcodeService
import ConSilkTea.SmallRecordServer.common.dto.BaseResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/food")
@RestController
class BarcodeController(
    private val barcodeService: BarcodeService
) {
    /**
     * 바코드로 음식정보 가져오기
     */
    @GetMapping("/barcode")
    fun searchBarcode(@RequestBody @Valid barcodeDtoRequest: BarcodeDtoRequest): BaseResponse<BarcodeDtoResponse> {
        val response = barcodeService.searchBarcodeInfo(barcodeDtoRequest.barcodeNum)
        return BaseResponse(data = response)
    }
}