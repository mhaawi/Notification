package com.edigitpath.notification.web.rest;

import com.edigitpath.notification.NotificationApp;

import com.edigitpath.notification.domain.SystemNotification;
import com.edigitpath.notification.repository.SystemNotificationRepository;
import com.edigitpath.notification.service.SystemNotificationService;
import com.edigitpath.notification.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.edigitpath.notification.web.rest.TestUtil.sameInstant;
import static com.edigitpath.notification.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.edigitpath.notification.domain.enumeration.StatusType;
/**
 * Test class for the SystemNotificationResource REST controller.
 *
 * @see SystemNotificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationApp.class)
public class SystemNotificationResourceIntTest {

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final StatusType DEFAULT_STATUS = StatusType.READ;
    private static final StatusType UPDATED_STATUS = StatusType.UN_READ;

    private static final ZonedDateTime DEFAULT_SEND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_KEY = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR = "AAAAAAAAAA";
    private static final String UPDATED_ERROR = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_SHORT_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ERROR_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ERROR_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final ZonedDateTime DEFAULT_DELETED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DELETED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SystemNotificationRepository systemNotificationRepository;

    @Autowired
    private SystemNotificationService systemNotificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSystemNotificationMockMvc;

    private SystemNotification systemNotification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemNotificationResource systemNotificationResource = new SystemNotificationResource(systemNotificationService);
        this.restSystemNotificationMockMvc = MockMvcBuilders.standaloneSetup(systemNotificationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemNotification createEntity(EntityManager em) {
        SystemNotification systemNotification = new SystemNotification()
            .content(DEFAULT_CONTENT)
            .status(DEFAULT_STATUS)
            .sendDate(DEFAULT_SEND_DATE)
            .sourceId(DEFAULT_SOURCE_ID)
            .sourceType(DEFAULT_SOURCE_TYPE)
            .sourceName(DEFAULT_SOURCE_NAME)
            .applicationKey(DEFAULT_APPLICATION_KEY)
            .error(DEFAULT_ERROR)
            .errorShortName(DEFAULT_ERROR_SHORT_NAME)
            .errorDate(DEFAULT_ERROR_DATE)
            .deleted(DEFAULT_DELETED)
            .deletedDate(DEFAULT_DELETED_DATE);
        return systemNotification;
    }

    @Before
    public void initTest() {
        systemNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemNotification() throws Exception {
        int databaseSizeBeforeCreate = systemNotificationRepository.findAll().size();

        // Create the SystemNotification
        restSystemNotificationMockMvc.perform(post("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemNotification)))
            .andExpect(status().isCreated());

        // Validate the SystemNotification in the database
        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        SystemNotification testSystemNotification = systemNotificationList.get(systemNotificationList.size() - 1);
        assertThat(testSystemNotification.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testSystemNotification.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSystemNotification.getSendDate()).isEqualTo(DEFAULT_SEND_DATE);
        assertThat(testSystemNotification.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testSystemNotification.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
        assertThat(testSystemNotification.getSourceName()).isEqualTo(DEFAULT_SOURCE_NAME);
        assertThat(testSystemNotification.getApplicationKey()).isEqualTo(DEFAULT_APPLICATION_KEY);
        assertThat(testSystemNotification.getError()).isEqualTo(DEFAULT_ERROR);
        assertThat(testSystemNotification.getErrorShortName()).isEqualTo(DEFAULT_ERROR_SHORT_NAME);
        assertThat(testSystemNotification.getErrorDate()).isEqualTo(DEFAULT_ERROR_DATE);
        assertThat(testSystemNotification.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testSystemNotification.getDeletedDate()).isEqualTo(DEFAULT_DELETED_DATE);
    }

    @Test
    @Transactional
    public void createSystemNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemNotificationRepository.findAll().size();

        // Create the SystemNotification with an existing ID
        systemNotification.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemNotificationMockMvc.perform(post("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemNotification)))
            .andExpect(status().isBadRequest());

        // Validate the SystemNotification in the database
        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemNotificationRepository.findAll().size();
        // set the field null
        systemNotification.setContent(null);

        // Create the SystemNotification, which fails.

        restSystemNotificationMockMvc.perform(post("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemNotification)))
            .andExpect(status().isBadRequest());

        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemNotificationRepository.findAll().size();
        // set the field null
        systemNotification.setStatus(null);

        // Create the SystemNotification, which fails.

        restSystemNotificationMockMvc.perform(post("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemNotification)))
            .andExpect(status().isBadRequest());

        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemNotifications() throws Exception {
        // Initialize the database
        systemNotificationRepository.saveAndFlush(systemNotification);

        // Get all the systemNotificationList
        restSystemNotificationMockMvc.perform(get("/api/system-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].sendDate").value(hasItem(sameInstant(DEFAULT_SEND_DATE))))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID.toString())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sourceName").value(hasItem(DEFAULT_SOURCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].applicationKey").value(hasItem(DEFAULT_APPLICATION_KEY.toString())))
            .andExpect(jsonPath("$.[*].error").value(hasItem(DEFAULT_ERROR.toString())))
            .andExpect(jsonPath("$.[*].errorShortName").value(hasItem(DEFAULT_ERROR_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].errorDate").value(hasItem(sameInstant(DEFAULT_ERROR_DATE))))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].deletedDate").value(hasItem(sameInstant(DEFAULT_DELETED_DATE))));
    }
    
    @Test
    @Transactional
    public void getSystemNotification() throws Exception {
        // Initialize the database
        systemNotificationRepository.saveAndFlush(systemNotification);

        // Get the systemNotification
        restSystemNotificationMockMvc.perform(get("/api/system-notifications/{id}", systemNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemNotification.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.sendDate").value(sameInstant(DEFAULT_SEND_DATE)))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID.toString()))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE.toString()))
            .andExpect(jsonPath("$.sourceName").value(DEFAULT_SOURCE_NAME.toString()))
            .andExpect(jsonPath("$.applicationKey").value(DEFAULT_APPLICATION_KEY.toString()))
            .andExpect(jsonPath("$.error").value(DEFAULT_ERROR.toString()))
            .andExpect(jsonPath("$.errorShortName").value(DEFAULT_ERROR_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.errorDate").value(sameInstant(DEFAULT_ERROR_DATE)))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.deletedDate").value(sameInstant(DEFAULT_DELETED_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingSystemNotification() throws Exception {
        // Get the systemNotification
        restSystemNotificationMockMvc.perform(get("/api/system-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemNotification() throws Exception {
        // Initialize the database
        systemNotificationService.save(systemNotification);

        int databaseSizeBeforeUpdate = systemNotificationRepository.findAll().size();

        // Update the systemNotification
        SystemNotification updatedSystemNotification = systemNotificationRepository.findById(systemNotification.getId()).get();
        // Disconnect from session so that the updates on updatedSystemNotification are not directly saved in db
        em.detach(updatedSystemNotification);
        updatedSystemNotification
            .content(UPDATED_CONTENT)
            .status(UPDATED_STATUS)
            .sendDate(UPDATED_SEND_DATE)
            .sourceId(UPDATED_SOURCE_ID)
            .sourceType(UPDATED_SOURCE_TYPE)
            .sourceName(UPDATED_SOURCE_NAME)
            .applicationKey(UPDATED_APPLICATION_KEY)
            .error(UPDATED_ERROR)
            .errorShortName(UPDATED_ERROR_SHORT_NAME)
            .errorDate(UPDATED_ERROR_DATE)
            .deleted(UPDATED_DELETED)
            .deletedDate(UPDATED_DELETED_DATE);

        restSystemNotificationMockMvc.perform(put("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSystemNotification)))
            .andExpect(status().isOk());

        // Validate the SystemNotification in the database
        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeUpdate);
        SystemNotification testSystemNotification = systemNotificationList.get(systemNotificationList.size() - 1);
        assertThat(testSystemNotification.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSystemNotification.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSystemNotification.getSendDate()).isEqualTo(UPDATED_SEND_DATE);
        assertThat(testSystemNotification.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testSystemNotification.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
        assertThat(testSystemNotification.getSourceName()).isEqualTo(UPDATED_SOURCE_NAME);
        assertThat(testSystemNotification.getApplicationKey()).isEqualTo(UPDATED_APPLICATION_KEY);
        assertThat(testSystemNotification.getError()).isEqualTo(UPDATED_ERROR);
        assertThat(testSystemNotification.getErrorShortName()).isEqualTo(UPDATED_ERROR_SHORT_NAME);
        assertThat(testSystemNotification.getErrorDate()).isEqualTo(UPDATED_ERROR_DATE);
        assertThat(testSystemNotification.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testSystemNotification.getDeletedDate()).isEqualTo(UPDATED_DELETED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemNotification() throws Exception {
        int databaseSizeBeforeUpdate = systemNotificationRepository.findAll().size();

        // Create the SystemNotification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemNotificationMockMvc.perform(put("/api/system-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemNotification)))
            .andExpect(status().isBadRequest());

        // Validate the SystemNotification in the database
        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemNotification() throws Exception {
        // Initialize the database
        systemNotificationService.save(systemNotification);

        int databaseSizeBeforeDelete = systemNotificationRepository.findAll().size();

        // Delete the systemNotification
        restSystemNotificationMockMvc.perform(delete("/api/system-notifications/{id}", systemNotification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemNotification> systemNotificationList = systemNotificationRepository.findAll();
        assertThat(systemNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemNotification.class);
        SystemNotification systemNotification1 = new SystemNotification();
        systemNotification1.setId(1L);
        SystemNotification systemNotification2 = new SystemNotification();
        systemNotification2.setId(systemNotification1.getId());
        assertThat(systemNotification1).isEqualTo(systemNotification2);
        systemNotification2.setId(2L);
        assertThat(systemNotification1).isNotEqualTo(systemNotification2);
        systemNotification1.setId(null);
        assertThat(systemNotification1).isNotEqualTo(systemNotification2);
    }
}
