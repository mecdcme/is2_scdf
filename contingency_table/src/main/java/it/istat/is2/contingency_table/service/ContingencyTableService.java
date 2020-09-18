package it.istat.is2.contingency_table.service;

import it.istat.is2.app.bean.SessionBean;
import it.istat.is2.workflow.dao.StepInstanceDao;
import it.istat.is2.workflow.domain.DataProcessing;
import it.istat.is2.workflow.domain.StepInstance;
import it.istat.is2.workflow.engine.EngineFactory;
import it.istat.is2.workflow.engine.EngineService;
import it.istat.is2.workflow.service.WorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class ContingencyTableService {

    @Autowired
    private StepInstanceDao stepInstanceDao;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    EngineFactory engineFactory;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private NotificatorService notificatorService;

    public static final Long STEP_INSTANCE_ID = 11L;

    public void contingecyTable(Long id) throws Exception {
        SessionBean sessionBean = new SessionBean();
        sessionBean.setId(id);
        this.httpSession.setAttribute("sessionBean", sessionBean);
        
        DataProcessing elaborazione = workflowService.findDataProcessing(id);
        StepInstance stepInstance = stepInstanceDao.findById(STEP_INSTANCE_ID).orElseThrow();

        notificatorService.createTask(elaborazione.getId(), stepInstance.getId());

        this.doStep(elaborazione, stepInstance);
    }

    public DataProcessing doStep(DataProcessing elaborazione, StepInstance stepInstance) throws Exception {
        EngineService engine = engineFactory.getEngine(stepInstance.getAppService().getEngineType());

        try {
            notificatorService.runningTask(elaborazione.getId(), stepInstance.getId());
            engine.init(elaborazione, stepInstance);
            engine.doAction();
            engine.processOutput();
            notificatorService.endTask(elaborazione.getId(), stepInstance.getId());
        } catch (Exception e) {
            notificatorService.errorTask(elaborazione.getId(), stepInstance.getId());
            e.printStackTrace();
            throw (e);
        } finally {
            engine.destroy();
        }
        return elaborazione;
    }
}
