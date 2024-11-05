package com.platine.myFitBuddy.features.fitSets.controller;

import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FitSetControllerImpl implements FitSetController {
  private final FitSetServiceImpl fitSetService;
}
