package ru.evotor.framework.receipt.position.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
import ru.evotor.framework.counterparties.collaboration.agent_scheme.TransactionOperator
import ru.evotor.framework.receipt.position.AgentRequisites
import java.lang.Exception

object AgentRequisitesMapper {

    private const val KEY_AGENT = "AGENT"
    private const val KEY_SUPPLIER = "SUPPLIER"
    private const val KEY_TRANSACTION_OPERATOR = "TRANSACTION_OPERATOR"
    private const val KEY_OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION"

    fun read(bundle: Bundle?): AgentRequisites? = bundle?.let {
        try {
            AgentRequisites(
                    Agent.from(it.getBundle(KEY_AGENT)) ?: return null,
                    Supplier.from(it.getBundle(KEY_SUPPLIER)) ?: return null,
                    TransactionOperator.from(it.getBundle(KEY_TRANSACTION_OPERATOR)),
                    it.getString(KEY_OPERATION_DESCRIPTION)
            )
        } catch (e: Exception) {
            null
        }
    }

    fun write(agentRequisites: AgentRequisites) = Bundle().apply {
        this.putBundle(KEY_AGENT, agentRequisites.agent.toBundle())
        this.putBundle(KEY_SUPPLIER, agentRequisites.supplier.toBundle())
        this.putBundle(KEY_TRANSACTION_OPERATOR, agentRequisites.transactionOperator?.toBundle())
        this.putString(KEY_OPERATION_DESCRIPTION, agentRequisites.operationDescription)
    }

}