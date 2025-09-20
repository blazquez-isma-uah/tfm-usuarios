package com.tfm.bandas.usuarios.model.specifications;

import com.tfm.bandas.usuarios.model.entity.Instrument;
import org.springframework.data.jpa.domain.Specification;

public class InstrumentSpecifications {

    public static Specification<Instrument> nameContains(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Instrument> voiceContains(String voice) {
        return (root, query, cb) ->
                voice == null ? null : cb.like(cb.lower(root.get("voice")), "%" + voice.toLowerCase() + "%");
    }
}
