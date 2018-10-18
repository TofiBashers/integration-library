package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

class CashWithdrawnEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashWithdrawnEvent? = bundle?.let {
            CashWithdrawnEvent(
                    getDocumentUuid(it) ?: return null,
                    getTotal(it) ?: return null
            )
        }
    }
}