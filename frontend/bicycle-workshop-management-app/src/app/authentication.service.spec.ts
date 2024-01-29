import { TestBed } from '@angular/core/testing';

import { AuthenticationServiceService } from './authentication-service.service';

describe('AuthenticationService', () => {
  let service: AuthenticationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
