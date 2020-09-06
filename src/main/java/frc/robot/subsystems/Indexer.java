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

public class Indexer extends SubsystemBase {
  VictorSP m_leftIndexer;
  VictorSP m_rightIndexer;
  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    m_leftIndexer = new VictorSP(Constants.INDEX_MOTOR1);
    m_rightIndexer = new VictorSP(Constants.INDEX_MOTOR2);
  }

  public void in(double in) {
    if( in > 0.2) {
      m_leftIndexer.set(-0.5 * in);
      m_rightIndexer.set(0.5 * in);
    }
    else {
      m_leftIndexer.set(0);
      m_rightIndexer.set(0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
