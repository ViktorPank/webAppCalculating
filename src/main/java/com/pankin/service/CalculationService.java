package com.pankin.service;

import com.pankin.dto.DTOCalculations;
import com.pankin.entity.Calculations;
import com.pankin.entity.LexicOrderArray;
import com.pankin.entity.NumberExpanded;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    /**
     * Task selection and processing
     *
     * @param dtoCalculations data for processing
     * @return Calculated data
     */
    public DTOCalculations calculations(DTOCalculations dtoCalculations) {

        String rawData = dtoCalculations.getRawData();


        if (rawData.contains("#NumberExpanded#\n") || rawData.contains("#NumberExpanded#\r\n")) {
            if (rawData.contains("#NumberExpanded#\n"))
                dtoCalculations.setData(rawData.replace("#NumberExpanded#\n", ""));
            if (rawData.contains("#NumberExpanded#\r\n"))
                dtoCalculations.setData(rawData.replace("#NumberExpanded#\r\n", ""));
            dtoCalculations.setTypeExample("NumberExpanded");

            if (dtoCalculations.getData().equals("")) new DTOCalculations("Пустое поле");
            Calculations calculations = new NumberExpanded(dtoCalculations.getData());
            dtoCalculations.setResultData(calculations.run().toString());
            return dtoCalculations;
        }
        if (rawData.contains("#LexicOrderArray#\n") || rawData.contains("#LexicOrderArray#\r\n")) {
            if (rawData.contains("#LexicOrderArray#\n"))
                dtoCalculations.setData(rawData.replace("#LexicOrderArray#\n", ""));
            if (rawData.contains("#LexicOrderArray#\r\n"))
                dtoCalculations.setData(rawData.replace("#LexicOrderArray#\r\n", ""));
            dtoCalculations.setTypeExample("LexicOrderArray");

            if (dtoCalculations.getData().equals("")) new DTOCalculations("Пустое поле");
            Calculations calculations = new LexicOrderArray(dtoCalculations.getData());
            dtoCalculations.setResultData(calculations.run().toString());
            return dtoCalculations;
        }

        DTOCalculations errorDTOCalculation = new DTOCalculations();
        errorDTOCalculation.setResultData("Неизвестная ошибка");
        return errorDTOCalculation;
    }

}
