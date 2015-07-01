(ns demo.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.q_functions :refer :all]))

(defn -main
  "I do everything"
  [& args]
  (cond (= (first args) 1)
        run1
        (= (first args) 2)
        run2
        (= (first args) 3)
        run3))

(def run1
  (defn track-to-pos [poses step]
  (loop [args poses
         vect []]
    (if (not= (count args) 0)
      (let [x (:x (first args))
            y (:y (first args))
            mx (q/mouse-x)
            my (q/mouse-y)
            dx (+ 0.1 (q/abs (- mx x)))
            dy (+ 0.1 (q/abs (- my y)))
            hyp (q/sqrt (+ (q/sq dx) (q/sq dy)))
            step-x (* dx (/ step hyp))
            step-y (* dy (/ step hyp))]

        (recur  (rest args) (conj vect {:x (cond (< x mx)
                                                 (+ x step-x)
                                                 (> x mx)
                                                 (- x step-x)
                                                 :else
                                                 x)
                                        :y (cond (< y my)
                                                 (+ y step-y)
                                                 (> y my)
                                                 (- y step-y)
                                                 :else
                                                 y)
                                        :shape (:shape (first args))})))
        vect)))




;-----------------------------------------------------------
;                   SKETCH FUNCTIONS

(defn setup []
    (q/frame-rate 45)
    (q/color-mode :rgb)

    (def sqr1 (create-rect 20 20 255 80 80))

{:step 2
     :angle 0
     :angle2 q/HALF-PI
     :pos [{:x 500 :y 500 :shape sqr1}
          ; {:x 400 :y 600 :shape sqr1}
          ; {:x 300 :y 700 :shape sqr1}
          ; {:x 200 :y 800 :shape sqr1}
           {:x 100 :y 900 :shape sqr1}]})



;-
(defn update-state [state]
    (assert (not-nil? state) "Your state is nil")

    (assoc state
      :angle (- (:angle state) 0.04)
      :angle2 (- (:angle2 state) 0.02)

      ;:pos (track-to-pos (:pos state) (:step state))
      ))




;-
(defn draw-state [state]
    (f-background 25 25 25 25)

    (doseq [i (range (count (:pos state)))]
      (ds (:shape (nth (:pos state) i)) (:x (nth (:pos state) i)) (:y (nth (:pos state) i))))

;-
(q/defsketch start
  :title "A Day at the Beach"
  :size [100 100]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])))










(def run2
  (defn track-to-pos [poses step]
  (loop [args poses
         vect []]
    (if (not= (count args) 0)
      (let [x (:x (first args))
            y (:y (first args))
            mx (q/mouse-x)
            my (q/mouse-y)
            dx (+ 0.1 (q/abs (- mx x)))
            dy (+ 0.1 (q/abs (- my y)))
            hyp (q/sqrt (+ (q/sq dx) (q/sq dy)))
            step-x (* dx (/ step hyp))
            step-y (* dy (/ step hyp))]

        (recur  (rest args) (conj vect {:x (cond (< x mx)
                                                 (+ x step-x)
                                                 (> x mx)
                                                 (- x step-x)
                                                 :else
                                                 x)
                                        :y (cond (< y my)
                                                 (+ y step-y)
                                                 (> y my)
                                                 (- y step-y)
                                                 :else
                                                 y)
                                        :shape (:shape (first args))})))
        vect)))






;-----------------------------------------------------------
;                   SKETCH FUNCTIONS

(defn setup []
    (q/frame-rate 45)
    (q/color-mode :rgb)


    (def sqr1 (create-rect 20 20 255 80 80))
    (def sqr2 (create-rect 40 40 80 255 80))
    (def sqr3 (create-rect 60 60 80 255 255))
    (def sqr4 (create-rect 80 80 :blue))
    (def sqr5 (create-rect 100 100 255 80 255))
    (def sqr6 (create-rect 120 120 80 255 255))
    (def sqr7 (create-rect 140 140 80 80 80))
    (def sqr8 (create-rect 160 160 255 255 255))
    (def sqr9 (create-rect 180 180 255 0 0))
    (def sqr10 (create-rect 200 200 0 80 80))
    (def sweet-line (create-line 0 200 0))
    (def long-box (create-rect 100 40 80 255 255))
    (def tall-box (create-rect 40 100 80 80 255))
    (def rich (scale-shape (create-picture "/home/hagen715/Desktop/images/rich_hickey.png") 0.3 0.3))
    (def rot-rich (rotate-shape rich 270))
    (def kappa (create-picture "/home/hagen715/Desktop/images/kappa96x130.png"))
    (def frankerz (create-picture "/home/hagen715/Desktop/images/frankerz220x200.jpg"))


;;     ;-----------------------------------------------------------
;;     (let [line-length 800
;;           box-length 800]

;;     (def diag-line (create-line (dec line-length) (dec line-length) 255 100))

;;     (def inviz-block (create-rect box-length box-length)))

;;     (def block-line (above diag-line
;;                            inviz-block))

;;     (def row (loop [w (/ (+ 200 (q/width)) (:tw (first block-line)))
;;                     shape block-line]
;;                (if (>= w 0)
;;                  (recur (dec w) (beside shape block-line))
;;                  shape)))

;;      (def lines (loop [h (/ (+ 200 (q/height)) (:th (first row)))
;;                     shape row]
;;                (if (>= h 0)
;;                  (recur (dec h) (above shape row))
;;                  shape)))
;;   {:angle 0
;;   :angle2 0}
    ;-----------------------------------------------------------

    (def red-rect
	  (create-rect 50 45 :red))

    (def door (create-rect 12 18 :brown))
    (def window (create-ellipse 10 10 :cyan))

    (def roof (create-triangle 60 0 30 -25 :brown))

    (def house (above (overlay window
                               roof)
                      (overlay-align :bottom :center
                                     door
                                     red-rect)))


    (def volley-ball (overlay (create-arc 100 100 0 (/ q/PI 3) 255 80 80)
                              (create-arc 100 100 (/ q/PI 3) (/ (* 2 q/PI) 3) 255 255 80)
                              (create-arc 100 100 (/ (* 2 q/PI) 3) q/PI 80 80 255)
                              (create-arc 100 100 q/PI (/ (* 4 q/PI) 3) :orange)
                              (create-arc 100 100 (/ (* 4 q/PI) 3) (/ (* 5 q/PI) 3) 255 80 255)
                              (create-arc 100 100 (/ (* 5 q/PI) 3) q/TWO-PI :teal)
                              (create-ellipse 100 100 255)))


    (def player-1 (create-rect 100 100 :lime))
    (def player-2 (create-rect 100 100 :cyan))
    (def pole-1 (create-line 0 500 100))


    (def beach (create-picture "/home/hagen715/Desktop/images/beach.jpg"))




{:step 2
     :angle 0
     :angle2 q/HALF-PI
     :pos [{:x 500 :y 500 :shape sqr1}
          ; {:x 400 :y 600 :shape sqr1}
          ; {:x 300 :y 700 :shape sqr1}
          ; {:x 200 :y 800 :shape sqr1}
           {:x 100 :y 900 :shape sqr1}]}





    (catch Throwable e (println (.getCause e)) (display-error (prettify-exception e))))

;-
(defn update-state [state]
    (assert (not-nil? state) "Your state is nil")




    (assoc state
      :angle (- (:angle state) 0.04)
      :angle2 (- (:angle2 state) 0.02)

      ;:pos (track-to-pos (:pos state) (:step state))
      )


    (catch Throwable e (println (.getCause e)) (display-error (prettify-exception e))))

;-
(defn draw-state [state]
    ;(f-background 25 25 25 25)
    ;-----------------------------------------------------------
;;     (f-stroke 255 80)
;;     (let [half-w (/ (q/width) 2)
;;           half-h (/ (q/height) 2)
;;           move-dist (:tw (first block-line))
;;           quarter-row (/ (:th (first row)) 4)]

;;     (ds lines (+ half-w (* (q/sin (:angle state)) move-dist)) (- half-h quarter-row))
;;     (ds lines (+ half-w (* (q/sin (:angle2 state)) move-dist)) (+ half-h quarter-row)))
    ;-----------------------------------------------------------


;;     (doseq [i (range (count (:pos state)))]
;;       (ds (:shape (nth (:pos state) i)) (:x (nth (:pos state) i)) (:y (nth (:pos state) i))))
    ;-----------------------------------------------------------


    ;-----------------------------------------------------------
;;     (def shape (scale-shape (create-rect 100 100 80 255 80) (+ 1.1 (q/sin (:angle state))) (+ 1.1 (q/sin (:angle state)))))
;;     (def other-shape (above (scale-shape sqr3 (+ 1.1 (q/sin (:angle2 state))) (+ 1.1 (q/sin (:angle2 state)))) shape (scale-image sqr3 (+ 1.1 (q/sin (:angle2 state))) (+ 1.1 (q/sin (:angle2 state))))))
;;     (ds other-shape 500 500)
    ;-----------------------------------------------------------


;;     (ds beach 500 500)

;;     (let
;;       [angle (:angle state)
;;        angle2 (:angle2 state)
;;        sin-a2 (q/sin angle2)]
;;     (q/no-stroke)
;;     (ds (scale-shape player-1
;;                      0.8
;;                      (* 0.8 (* 2 (q/sin (+ q/HALF-PI angle2)))))
;;         150
;;         (+ 610 (* 75 (q/sin (+ q/PI angle)))))

;;     (ds (scale-shape player-2
;;                      1
;;                      (* 2 sin-a2))
;;         850
;;         (+ 850 (* 75 (q/sin angle))))

;;     (q/stroke-weight 10)
;;     (ds pole-1 300 800)
;;     (q/stroke-weight 5)
;;     (ds (scale-shape pole-1 0.6 0.6) 775 750)
;;     (q/stroke-weight 1)
;;     (q/no-stroke)

;;     (ds (scale-shape (vec (map #(rotate-shape % (* 40 angle)) volley-ball))
;;                      (+ 0.8 (* 0.2 (q/abs sin-a2)))
;;                      (+ 0.8 (* 0.2 (q/abs sin-a2))))
;;         (+ 500 (* 350 (q/cos angle)))

;;         (if (> (+ 500 (* 350 (q/cos angle)))  500)
;;           (- 700 (* 500 (q/sin (mod angle q/PI))))
;;           (- 500 (* 300 (q/sin (mod angle q/PI))))))



                      ;(overlay-align :bottom :center
                      ;               door
                       ;              red-rect)))




(f-background 255)
            (q/stroke 0)
	  				(ds (overlay-align :bottom :center
                                     door
                                     red-rect) (/ (q/width) 2) (/ (q/height) 2))


    (if (= (q/frame-count) 1) (q/save-frame "body.png"))



    (catch Throwable e (println (.getCause e)) (display-error (prettify-exception e))))


;-
(q/defsketch start
  :title "A Day at the Beach"
  :size [100 100]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode]))
