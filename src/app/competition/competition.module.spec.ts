import { CompetitionsModule } from './competitions.module';

describe('CompetitionModule', () => {
  let competitionModule: CompetitionsModule;

  beforeEach(() => {
    competitionModule = new CompetitionsModule();
  });

  it('should create an instance', () => {
    expect(competitionModule).toBeTruthy();
  });
});
