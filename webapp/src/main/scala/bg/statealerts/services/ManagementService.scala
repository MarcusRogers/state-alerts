package bg.statealerts.services

import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Component
import javax.inject.Inject
import org.springframework.jmx.export.annotation.ManagedOperation
import bg.statealerts.scheduled.AlertJob
import bg.statealerts.util.TestProfile

@ManagedResource
@Component
@TestProfile.Disabled
class ManagementService {

  @Inject
  var indexer: Indexer = _

  @Inject
  var alertJob: AlertJob = _

  @Inject
  var mailServoce: MailService = _

  @Inject
  var messagePreparators: MessagePreparators = _

  @ManagedOperation
  def reindex() = {
    indexer.reindex
  }

  @ManagedOperation
  def sendAlerts() = {
    alertJob.send
  }

  @ManagedOperation
  def resendFailedAlerts() = {
    alertJob.resendFailed
  }

  @ManagedOperation
  def sendTestMail(to: String) = {
    mailServoce.send(messagePreparators.testMail(to))
  }

}