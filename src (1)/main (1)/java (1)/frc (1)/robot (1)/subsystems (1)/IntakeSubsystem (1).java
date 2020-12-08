/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private final Talon m_intake;
  private final DoubleSolenoid m_intakePistons;
  private boolean intakeIsIn = true;
  
  public IntakeSubsystem() {

    m_intake = new Talon(Constants.INTAKE_MOTOR);
    m_intakePistons = new DoubleSolenoid(0,1);

  }

  public void pistonPush() {
    
    if(intakeIsIn) {
      m_intakePistons.set(Value.kForward);
      intakeIsIn = false;
    }
    else {
      m_intakePistons.set(Value.kReverse);
      intakeIsIn = true;
    }
  }

  public void intake(double intake){
    if(intake > 0.2)
      m_intake.set(intake);
    else 
      m_intake.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
