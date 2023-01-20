package com.testproject.coffeehouseapi.util;

import com.testproject.coffeehouseapi.exception.RequestStatusException;
import com.testproject.coffeehouseapi.model.CategoryEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class CategoryEnumConverter implements AttributeConverter<CategoryEnum, String> {

    @Override
    public String convertToDatabaseColumn(CategoryEnum categoryEnum) {
        return categoryEnum.getDbValue();
    }

    @Override
    public CategoryEnum convertToEntityAttribute(String dbValue) {
        for (CategoryEnum e : CategoryEnum.values()) {
            if (e.getDbValue().equals(dbValue)) {
                return e;
            }
        }
        log.error("Unable to convert database value \"{}\" to enum {}.", dbValue, CategoryEnum.class.getName());
        throw new RequestStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
