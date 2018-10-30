package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Supplier
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper
import java.lang.Exception

object SupplierMapper {

    fun read(bundle: Bundle?): Supplier? =
            CounterpartyMapper.read(bundle)?.let {
                Supplier(
                        uuid = it.uuid,
                        counterpartyType = it.counterpartyType,
                        fullName = it.fullName,
                        shortName = it.shortName,
                        inn = it.inn,
                        kpp = it.kpp,
                        contacts = it.contacts
                )
            }

}