package com.tfm.bandas.usuarios.model.specification;

import com.tfm.bandas.usuarios.model.entity.UserProfile;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<UserProfile> firstNameContains(String firstName) {
        return (root, query, cb) ->
                firstName == null ? null : cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<UserProfile> lastNameContains(String lastName) {
        return (root, query, cb) ->
                lastName == null ? null : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<UserProfile> emailContains(String email) {
        return (root, query, cb) ->
                email == null ? null : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<UserProfile> activeIs(Boolean active) {
        return (root, query, cb) ->
                active == null ? null : cb.equal(root.get("active"), active);
    }

    public static Specification<UserProfile> hasInstrument(Long instrumentId) {
        return (root, query, cb) -> {
            if (instrumentId == null) return null;
            Join<UserProfile, Instrument> instruments = root.join("instruments");
            return cb.equal(instruments.get("id"), instrumentId);
        };
    }
}
