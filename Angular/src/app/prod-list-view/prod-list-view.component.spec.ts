import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdListViewComponent } from './prod-list-view.component';

describe('ProdListViewComponent', () => {
  let component: ProdListViewComponent;
  let fixture: ComponentFixture<ProdListViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdListViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdListViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
