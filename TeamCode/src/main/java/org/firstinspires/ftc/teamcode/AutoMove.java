/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Auto Move 2021", group="Autonomous")
public class AutoMove extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontDrive, backDrive, leftDrive, rightDrive;

    private CRServo lifter, pusher;
    private DcMotor leftRotator, rightRotator, leftShooter, rightShooter;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        try {
            frontDrive = hardwareMap.get(DcMotor.class, "FrontMotor");
            backDrive = hardwareMap.get(DcMotor.class, "BackMotor");
            leftDrive = hardwareMap.get(DcMotor.class, "LeftMotor");
            rightDrive = hardwareMap.get(DcMotor.class, "RightMotor");

            leftRotator = hardwareMap.get(DcMotor.class, "LeftRotator");
            rightRotator = hardwareMap.get(DcMotor.class, "RightRotator");

            lifter = hardwareMap.get(CRServo.class, "Lifter");
            pusher = hardwareMap.get(CRServo.class, "Pusher");

            leftShooter = hardwareMap.get(DcMotor.class, "LeftShooter");
            rightShooter = hardwareMap.get(DcMotor.class, "RightShooter");
        } catch(Exception e) {
            telemetry.addData("Status", "Mapping Failure.");
            throw e;
        }
        telemetry.addData("Status", "Mapping Completed.");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontDrive.setDirection(DcMotor.Direction.FORWARD);
        backDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);


        // Tell the driver that initialization is complete.

        telemetry.addData("Status", "Initialized");
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        leftShooter.setPower(-1);
        rightShooter.setPower(1);

        runtime.reset();
        leftDrive.setPower(0.98);
        rightDrive.setPower(1);
        while(runtime.milliseconds() < 8000);

        runtime.reset();
        leftDrive.setPower(-0.98);
        rightDrive.setPower(-1);
        while(runtime.milliseconds() < 2600);

        telemetry.update();
    }
}
