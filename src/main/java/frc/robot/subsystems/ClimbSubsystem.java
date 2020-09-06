/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  
  DoubleSolenoid m_climbPiston;
  boolean pushIsIn = false;
  Talon m_climbMotor;

  public ClimbSubsystem() {
    m_climbPiston = new DoubleSolenoid(Constants.CLIMB_SOLENOID_DEPLOY, Constants.CLIMB_SOLENOID_RETRACT);
    m_climbMotor = new Talon(Constants.CLIMB_MOTOR);
  }
  
  public void climbMotor(double climb) {
    m_climbMotor.set(-climb);
  }

  public void climbPistons() 
  {
    if(pushIsIn){
      m_climbPiston.set(Value.kForward);
      pushIsIn = false;
    }
    else {
      m_climbPiston.set(Value.kReverse);
      pushIsIn = true;
    }  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}