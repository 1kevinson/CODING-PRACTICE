package com.wealcome.wealhome.write.adapters.primary;

import com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch.LaunchCallForFundsCommand;
import com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch.LaunchCallForFundsCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.fromString;

@RestController
public class CallsForFundsController {

    private final LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler;

    public CallsForFundsController(LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler) {
        this.launchCallForFundsCommandHandler = launchCallForFundsCommandHandler;
    }

    @PostMapping(path = "fiscalyears/{fiscalYearId}/callsforfunds")
    public ResponseEntity<Void> create(@PathVariable("fiscalYearId") String fiscalYearId) {
        launchCallForFundsCommandHandler.handle(new LaunchCallForFundsCommand(fromString(fiscalYearId)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
