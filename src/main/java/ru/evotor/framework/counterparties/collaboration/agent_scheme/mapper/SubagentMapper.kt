package ru.evotor.framework.counterparties.collaboration.agent_scheme.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.counterparties.mapper.CounterpartyMapper

object SubagentMapper {

    private const val KEY_TYPE = "TYPE"

    fun read(bundle: Bundle?): Subagent? =
            bundle?.let {
                Subagent(
                        uuid = CounterpartyMapper.readUuid(it),
                        type = Subagent.Type.values()[it.getInt(KEY_TYPE)],
                        counterpartyType = CounterpartyMapper.readCounterpartyType(it),
                        fullName = CounterpartyMapper.readFullName(it),
                        shortName = CounterpartyMapper.readShortName(it),
                        inn = CounterpartyMapper.readInn(it),
                        kpp = CounterpartyMapper.readKpp(it),
                        contacts = CounterpartyMapper.readContacts(it)
                )
            }

    fun write(agent: Subagent, bundle: Bundle) = bundle.apply {
        agent.type.let { this.putInt(KEY_TYPE, it.ordinal) }
    }

}