package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

public interface ResultRegistrationService {

    Long registerResults(ResultRegistrationForm resultRegistrationForm);

}
