import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchClubsComponent } from './search-clubs.component';

describe('SearchClubsComponent', () => {
  let component: SearchClubsComponent;
  let fixture: ComponentFixture<SearchClubsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchClubsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchClubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
