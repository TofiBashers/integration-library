package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

class CashInsertedEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashInsertedEvent? = bundle?.let {
            CashInsertedEvent(
                    getDocumentUuid(it) ?: return null,
                    getTotal(it) ?: return null
            )
        }
    }
}