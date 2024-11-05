package com.platine.myFitBuddy.features.records.controller;

import com.platine.myFitBuddy.features.records.service.FitRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FitRecordControllerImpl {
  private final FitRecordServiceImpl recordService;
}
