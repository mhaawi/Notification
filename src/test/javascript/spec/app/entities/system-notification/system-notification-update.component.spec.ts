/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationTestModule } from '../../../test.module';
import { SystemNotificationUpdateComponent } from 'app/entities/system-notification/system-notification-update.component';
import { SystemNotificationService } from 'app/entities/system-notification/system-notification.service';
import { SystemNotification } from 'app/shared/model/system-notification.model';

describe('Component Tests', () => {
    describe('SystemNotification Management Update Component', () => {
        let comp: SystemNotificationUpdateComponent;
        let fixture: ComponentFixture<SystemNotificationUpdateComponent>;
        let service: SystemNotificationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationTestModule],
                declarations: [SystemNotificationUpdateComponent]
            })
                .overrideTemplate(SystemNotificationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemNotificationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemNotificationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SystemNotification(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.systemNotification = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SystemNotification();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.systemNotification = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
