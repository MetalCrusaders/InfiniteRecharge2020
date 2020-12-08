/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  VictorSP m_leftShooter;
  VictorSP m_rightShooter;


  public ShooterSubsystem() {
    m_leftShooter = new VictorSP(Constants.SHOOTER_LEFT_MOTOR);
    m_rightShooter = new VictorSP(Constants.SHOOTER_RIGHT_MOTOR);
  }

  public void shoot(double shoot) {
    
    if(shoot > .2){
      m_leftShooter.set(-shoot);
      m_rightShooter.set(shoot);
    }
    else {
    m_leftShooter.set(0);
    m_rightShooter.set(0);
    }

  }


  
  @Override
  public void periodic() {
    
  }
}
