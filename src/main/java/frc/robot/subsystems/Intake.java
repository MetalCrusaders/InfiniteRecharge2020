/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final Talon m_intake;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    m_intake = new Talon(Constants.INTAKE_TALON);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intake(double in) {
    if(in < 0.15) {
      m_intake.set(0);
    }
    else{
    m_intake.set(in);
    }
  }

  public void stop() {
    m_intake.set(0);
  }
}
