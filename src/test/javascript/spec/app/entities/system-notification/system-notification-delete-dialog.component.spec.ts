/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationTestModule } from '../../../test.module';
import { SystemNotificationDeleteDialogComponent } from 'app/entities/system-notification/system-notification-delete-dialog.component';
import { SystemNotificationService } from 'app/entities/system-notification/system-notification.service';

describe('Component Tests', () => {
    describe('SystemNotification Management Delete Component', () => {
        let comp: SystemNotificationDeleteDialogComponent;
        let fixture: ComponentFixture<SystemNotificationDeleteDialogComponent>;
        let service: SystemNotificationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationTestModule],
                declarations: [SystemNotificationDeleteDialogComponent]
            })
                .overrideTemplate(SystemNotificationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemNotificationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemNotificationService);
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
