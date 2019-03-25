/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationTestModule } from '../../../test.module';
import { SystemNotificationDetailComponent } from 'app/entities/system-notification/system-notification-detail.component';
import { SystemNotification } from 'app/shared/model/system-notification.model';

describe('Component Tests', () => {
    describe('SystemNotification Management Detail Component', () => {
        let comp: SystemNotificationDetailComponent;
        let fixture: ComponentFixture<SystemNotificationDetailComponent>;
        const route = ({ data: of({ systemNotification: new SystemNotification(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationTestModule],
                declarations: [SystemNotificationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemNotificationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemNotificationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemNotification).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
