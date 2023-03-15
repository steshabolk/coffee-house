package com.testproject.coffeehouseapi.dto.response;

import com.testproject.coffeehouseapi.dto.AvailableMenuDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AvailableMenuResponse {

    private List<AvailableMenuDto> menu;

}
