import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemNotification } from 'app/shared/model/system-notification.model';
import { SystemNotificationService } from './system-notification.service';

@Component({
    selector: 'jhi-system-notification-delete-dialog',
    templateUrl: './system-notification-delete-dialog.component.html'
})
export class SystemNotificationDeleteDialogComponent {
    systemNotification: ISystemNotification;

    constructor(
        protected systemNotificationService: SystemNotificationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.systemNotificationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'systemNotificationListModification',
                content: 'Deleted an systemNotification'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-system-notification-delete-popup',
    template: ''
})
export class SystemNotificationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemNotification }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SystemNotificationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.systemNotification = systemNotification;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/system-notification', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/system-notification', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
