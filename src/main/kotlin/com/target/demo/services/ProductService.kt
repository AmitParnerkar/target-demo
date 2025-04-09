package com.target.demo.services

import com.target.demo.model.Product
import com.target.demo.repo.ProductRepository
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream

@Service
class ProductService(val repository: ProductRepository) {
    fun saveProductsFromExcel(file: MultipartFile) {
        val inputStream: InputStream = file.inputStream
        val workbook = XSSFWorkbook(inputStream)
        val sheet = workbook.getSheetAt(0)
        val iterator = sheet.iterator()
        iterator.next() // Skip header row
        while (iterator.hasNext()) {
            val row = iterator.next()
            val product = Product(
                productName = row.getCell(0).stringCellValue,
                description = row.getCell(1).stringCellValue,
                material = row.getCell(2).stringCellValue,
                dimensions = row.getCell(3).stringCellValue,
                sku = row.getCell(4).stringCellValue,
                price = row.getCell(5).numericCellValue,
                category = row.getCell(6).stringCellValue,
                productRelationship = row.getCell(7).stringCellValue,
                inventoryQuantity = row.getCell(8).numericCellValue.toInt(),
                productImageUrl = row.getCell(9).stringCellValue,
                supplierInformation = row.getCell(10).stringCellValue,
                careInstructions = row.getCell(11).stringCellValue
            )
            repository.save(product)
        }
    }

    fun getAllProducts(): List<Product> = repository.findAll()
}