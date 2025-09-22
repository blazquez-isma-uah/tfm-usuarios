package com.tfm.bandas.usuarios.model.specification;

import com.tfm.bandas.usuarios.model.entity.InstrumentEntity;
import org.springframework.data.jpa.domain.Specification;

public class InstrumentSpecifications {

    public static Specification<InstrumentEntity> nameContains(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<InstrumentEntity> voiceContains(String voice) {
        return (root, query, cb) ->
                voice == null ? null : cb.like(cb.lower(root.get("voice")), "%" + voice.toLowerCase() + "%");
    }
}
