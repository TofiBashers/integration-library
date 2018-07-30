package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.CashOperationEventMapper
import java.math.BigDecimal

/**
 * Широковещательный приёмник событий денежных операциях.
 * @see <a href="https://developer.evotor.ru/docs/tobi_pizda">Использование широковещательного приёмника</a>
 */
open class CashOperationBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий внесения наличности.
     */
    @RequiresIntentAction(ACTION_CASH_IN)
    protected open fun handleCashInEvent(context: Context, documentUuid: String, total: BigDecimal) = Unit

    /**
     * Обработчик событий изъятия наличности.
     */
    @RequiresIntentAction(ACTION_CASH_OUT)
    protected open fun handleCashOutEvent(context: Context, documentUuid: String, total: BigDecimal) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        val mapper = CashOperationEventMapper(bundle)
        val documentUuid = mapper.getDocumentUuid() ?: return
        val total = mapper.getTotal() ?: return
        when (action) {
            ACTION_CASH_IN -> handleCashInEvent(context, documentUuid, total)
            ACTION_CASH_OUT -> handleCashOutEvent(context, documentUuid, total)
        }
    }

    companion object {

        const val ACTION_CASH_IN = "evotor.intent.action.cashOperation.CASH_IN"

        const val ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT"

    }

}