/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationTestModule } from '../../../test.module';
import { ApiDefinationDeleteDialogComponent } from 'app/entities/api-defination/api-defination-delete-dialog.component';
import { ApiDefinationService } from 'app/entities/api-defination/api-defination.service';

describe('Component Tests', () => {
    describe('ApiDefination Management Delete Component', () => {
        let comp: ApiDefinationDeleteDialogComponent;
        let fixture: ComponentFixture<ApiDefinationDeleteDialogComponent>;
        let service: ApiDefinationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationTestModule],
                declarations: [ApiDefinationDeleteDialogComponent]
            })
                .overrideTemplate(ApiDefinationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiDefinationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiDefinationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});