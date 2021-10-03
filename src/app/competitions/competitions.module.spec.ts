import { CompetitionsModule } from './competitions.module';

describe('CompetitionsModule', () => {
  let competitionModule: CompetitionsModule;

  beforeEach(() => {
    competitionModule = new CompetitionsModule();
  });

  it('should create an instance', () => {
    expect(competitionModule).toBeTruthy();
  });
});
