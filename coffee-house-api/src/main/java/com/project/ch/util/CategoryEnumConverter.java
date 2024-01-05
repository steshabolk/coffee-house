package com.project.ch.util;

import com.project.ch.entity.CategoryEnum;
import com.project.ch.exception.ApiResponseStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


@Converter(autoApply = true)
public class CategoryEnumConverter implements AttributeConverter<CategoryEnum, String> {

    Logger log = LoggerFactory.getLogger(CategoryEnumConverter.class);

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
        log.error("unable to convert database value \"{}\" to enum {}.", dbValue, CategoryEnum.class.getName());
        throw new ApiResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
