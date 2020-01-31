/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final Victor m_leftShooter;
  private final Victor m_rightShooter;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    m_leftShooter = new Victor(3);
    m_rightShooter = new Victor(4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double shoot) {
    m_leftShooter.set(shoot);
    m_rightShooter.set(-shoot);
  }

  public void stop() {
    m_leftShooter.set(0);
    m_rightShooter.set(0);
  }
}
